package com.yamamoto.raizesnordeste.exception;

import com.yamamoto.raizesnordeste.dto.ErroDetalheDTO;
import com.yamamoto.raizesnordeste.dto.ErroPadraoDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroPadraoDTO recursoNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request){
        return new ErroPadraoDTO(("RECURSO_NAO_ENCONTRADO"),
               ex.getMessage(),
               List.of(),
               Instant.now(),
               request.getRequestURI());
    }

    @ExceptionHandler(ConflitoNegocioException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroPadraoDTO conflitoNegocio(ConflitoNegocioException ex, HttpServletRequest request){
        return new ErroPadraoDTO(("CONFLITO_NEGOCIO"),
                ex.getMessage(),
                List.of(),
                Instant.now(),
                request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroPadraoDTO erroValidacao(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<ErroDetalheDTO> detalheDTOS = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ErroDetalheDTO(err.getField(), err.getDefaultMessage())).toList();
        return new ErroPadraoDTO(("ERRO_VALIDACAO"),
                ("Dados invalidos na requisicao ou enum nao reconhecido"),
                detalheDTOS,
                Instant.now(),
                request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroPadraoDTO requisicaoInvalida(HttpMessageNotReadableException ex, HttpServletRequest request){
        return new ErroPadraoDTO(("REQUISICAO_INVALIDA"),
                ("Corpo da requisicao invalido ou enum nao reconhecido"),
                List.of(),
                Instant.now(),
                request.getRequestURI());
    }
}
