package com.zhang.colas.common.utils.key;

public class SnowflakeIdWorker {

    /**
     * 开始时间戳（2015-01-01）
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id,结果是31（这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数）
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id,结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器id向左移12位
     */
    private final long workerIdShift = sequenceBits;


    /**
     * 数据标识id向左移17位
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳向左移22位
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095(0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器id（0~31）
     */
    private long workerId;

    /**
     * 数据中心id（0~31）
     */
    private long datacenterId;

    /**
     * 毫秒内序列（0~4095）
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次生成的时间戳，说明系统时钟回退过，这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                //阻塞到下一个毫秒，获取新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }

        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间戳
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获取新的时间
     *
     * @param lastTimestamp 上次生成Id的时间戳
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间（毫秒）
     */
    protected long timeGen() {
        return SystemClock.now();
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SnowflakeIdWorker idWorker0 = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 3; i++) {
            System.out.println(idWorker0.nextId());
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}
