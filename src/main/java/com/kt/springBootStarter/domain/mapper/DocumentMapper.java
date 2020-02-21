package com.kt.springBootStarter.domain.mapper;

import com.kt.springBootStarter.domain.dto.DocumentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocumentMapper {
    public Long insertDocument(DocumentDTO document);
    public void updateDocument(DocumentDTO document);
    public void deleteDocument(long id);
    public void countViewDocument(long id);
    public DocumentDTO selectById(long id);
    public List<DocumentDTO> getDocumentList();
}
