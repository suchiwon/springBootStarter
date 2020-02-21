package com.kt.springBootStarter.service;

import com.kt.springBootStarter.domain.dto.DocumentDTO;
import com.kt.springBootStarter.domain.mapper.DocumentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocumentService {

    @Resource
    private DocumentMapper documentMapper;

//    public Page<DocumentEntity> getDocumentList(Pageable pageable) {
//        pageable = PageRequest.of(
//                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
//                pageable.getPageSize());
//        return documentRepository.findAll(pageable);
//    }
    public List<DocumentDTO> getDocumentList() {
        return documentMapper.getDocumentList();
    }

    @Transactional
    public DocumentDTO getDocumentById(Long id) {
        DocumentDTO dto = documentMapper.selectById(id);

        documentMapper.countViewDocument(id);

        return dto;
    }

    @Transactional
    public Long writeDocument(DocumentDTO dto) {
        documentMapper.insertDocument(dto);
        return dto.getId();
    }

    @Transactional
    public void updateDocument(DocumentDTO dto) {
        documentMapper.updateDocument(dto);
    }

    public void deleteDocument(Long id) {
        documentMapper.deleteDocument(id);
    }
}
