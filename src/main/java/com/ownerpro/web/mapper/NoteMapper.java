package com.ownerpro.web.mapper;

import com.ownerpro.web.MyMapper;
import com.ownerpro.web.dto.NoteRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteMapper extends MyMapper<NoteRequest> {

    int add(Bean bean);
}

