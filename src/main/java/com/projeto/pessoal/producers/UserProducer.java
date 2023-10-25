package com.projeto.pessoal.producers;

import com.projeto.pessoal.data.v1.EmailDTO.EmailDTO;
import com.projeto.pessoal.model.Account;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${spring.rabbitmq.template.default-receive-queue}")
    private String routingKey;

    public void publishMessageEmail(Account accountModel) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(accountModel.getId());
        emailDto.setEmailTo(accountModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(accountModel.getUsername() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveita agora a nossa plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
