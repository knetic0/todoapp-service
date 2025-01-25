package com.pieces.todoapp.core.mapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
        ModelMapper forResponse();
        ModelMapper forRequest();
    }

