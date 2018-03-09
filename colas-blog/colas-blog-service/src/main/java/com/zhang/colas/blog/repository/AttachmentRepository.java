package com.zhang.colas.blog.repository;

import com.zhang.colas.blog.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {


    @Query("select t from Attachment t")
    List<Attachment> selectByMd5(String md5);
}
