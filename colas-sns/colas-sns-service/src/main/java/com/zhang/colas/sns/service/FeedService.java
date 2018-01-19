package com.zhang.colas.sns.service;

import com.zhang.colas.common.PageParams;
import com.zhang.colas.common.PageResult;

public interface FeedService {
    PageResult queryList(PageParams pageParams);
}
