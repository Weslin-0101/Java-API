package com.projeto.pessoal.docs.schemas;

import com.projeto.pessoal.docs.util.ErrorMessageUtil;
import io.swagger.v3.oas.annotations.media.Schema;

public class BadRequestSchema extends ErrorMessageUtil {
    public BadRequestSchema(String code, String message) {
        super(code, message);
    }

    @Override
    @Schema(example = "400", description = "Bad Request")
    public String getCode() {
        return super.getCode();
    }
}
