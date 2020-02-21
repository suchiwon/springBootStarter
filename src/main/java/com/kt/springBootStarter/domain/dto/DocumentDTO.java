package com.kt.springBootStarter.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.MappedTypes;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedTypes(LocalDateTime.class)
public class DocumentDTO {

        private Long id;
        private String user_id;
        private String title;
        private String content;
        private Date created_date;
        private Date updated_date;
        private int views;
}
