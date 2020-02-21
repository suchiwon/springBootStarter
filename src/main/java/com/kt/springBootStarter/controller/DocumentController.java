package com.kt.springBootStarter.controller;

import com.kt.springBootStarter.domain.dto.DocumentDTO;
import com.kt.springBootStarter.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@AllArgsConstructor
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/")
    public String main() {

        return "content.html";
        //return new RedirectView("/list");
    }

//    @GetMapping("/list")
//    public String board(@PageableDefault Pageable pageable, Model model) {
//        Page<DocumentEntity> documentList = documentService.getDocumentList(pageable);
//        documentList.stream().forEach(e -> e.getContent());
//        model.addAttribute("documentList", documentList);
//
//        return "document/list.html";
//    }
    @GetMapping("/list")
    public String board(Model model) {
        List<DocumentDTO> documentList = documentService.getDocumentList();
        model.addAttribute("documentList", documentList);

        return "document/list.html";
    }

    @GetMapping("/write")
    public String Write(Model model) {
        model.addAttribute("mode", "write");
//        model.addAttribute("document", new DocumentDTO.WriteDocumentDTO());
        return "document/write.html";
    }

    @PostMapping("/write")
    @Transactional
    public RedirectView WriteDocument(DocumentDTO document, @RequestParam(value="isError", defaultValue = "false") boolean isError) {
        Long id = documentService.writeDocument(document);

        if (isError) {
            throw new RuntimeException();
        }

        //transactional 테스트용 코드
        document.setId(id);
        document.setViews(1);
        documentService.updateDocument(document);

        return new RedirectView("/read?id=" + id);
    }

    @GetMapping("/read")
    public String ReadDocument(@RequestParam(value="id", defaultValue = "0") Long id,
                               Model model) {


        model.addAttribute("document", documentService.getDocumentById(id));
        return "/document/read.html";
    }

    @GetMapping("/update")
    public String UpdateView(@RequestParam(value="id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("mode", "update");
        model.addAttribute("document", documentService.getDocumentById(id));
        return "document/write.html";
    }

    @PostMapping("/update")
    @Transactional
    public RedirectView UpdateDocument(@ModelAttribute DocumentDTO document) {

        documentService.updateDocument(document);

        return new RedirectView("/read?id=" + document.getId());
    }

    @PostMapping("/delete")
    public RedirectView DeleteDocument(@ModelAttribute DocumentDTO document) {
        documentService.deleteDocument(document.getId());

        return new RedirectView("/list");
    }
}
