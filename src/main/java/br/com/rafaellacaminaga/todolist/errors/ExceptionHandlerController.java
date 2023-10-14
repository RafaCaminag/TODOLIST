package br.com.rafaellacaminaga.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// para costumizar o erro
// essa marcação ControllerAdvice é usada para definir classes globais no momento
// de deifinir o tratamento de exceções
// ou seja, toda exceção que for lançada vai passar por esse ControllerAdvice
@ControllerAdvice
public class ExceptionHandlerController {
    // aqui dentro colocaríamos outros tratamentos para outros tipos de exceção


    // metodo de tratamento de exceção do tipo "handleHttpMessageNotReadableException"
    // assim, de parametyro recebemos a exception pro seu metodo
    // temos que informar que ese metodo eh exatamente para esse tipo e exceção
    // portanto usamos essa ExceptionHandler annotation e passamos exatamente o tipo
    // de exceção que queremos tratar com essa método específico
    // e assim, toda exceção desse tipo vai passar por aqui antes de retornar pro user
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        // executar o seguinte ao tratar tal tipo de exceção
        // vamos retornar um status de erro de bad request e no body da mensagem estará a mesma...
        // ... mensagem que escrevemos no TaskModel quando lançamos a exceção lá
        // e essa tal mensagem será pega pela exceção do tipo de exceção passada de parametro
        // e nessa exceção que lançamos em TaskModel consta a mensagem (q tbm será usada aqui)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }


}
