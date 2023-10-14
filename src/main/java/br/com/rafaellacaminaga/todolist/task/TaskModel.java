package br.com.rafaellacaminaga.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator =  "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Try Catch PRÉ-GAMBIARRA
    // criando um método de set Title
    // colocamos um throws Exception pq passamos a responsabilidade de definir um tratamento da exceção...
    // pra quem está chamando o set Title (ou seja para camada acima)
    public void setTitle (String title) throws Exception {
        
        // vamos verificar se esse valor do title está respeitando nosso limite de length de 50 char
        if(title.length() > 50) {
            // vamos mostrar o erro lançando uma exceção (que são erros que conseguimos passar pro usuário)
            // exception é o tipo de exceção mais genérica que temos no java 
            // {existem exceções tratáveis e as nn tratáveis}
            // toda vez que lançamos uma exceção temos que tratá-la, definir um tratamento
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        
        // isso aqui é o que o próprio set Title já faz por padrão
        this.title = title;
    }


}
