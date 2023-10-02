package com.learning.springbootkubernetes.service;

import com.learning.springbootkubernetes.dto.BookmarkDTO;
import com.learning.springbootkubernetes.dto.BookmarkMapper;
import com.learning.springbootkubernetes.dto.BookmarksDTO;
import com.learning.springbootkubernetes.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public BookmarksDTO getBookMarks(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.ASC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = bookmarkRepository.findBookmarks(pageable) ;
        return new BookmarksDTO(bookmarkPage);
    }
}
