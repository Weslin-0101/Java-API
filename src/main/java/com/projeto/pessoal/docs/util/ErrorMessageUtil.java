package com.projeto.pessoal.docs.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessageUtil {
    private String code;
    private String message;
}
