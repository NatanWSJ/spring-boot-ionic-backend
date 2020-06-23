package com.natanjesus.cursomc.services.validation;

import com.natanjesus.cursomc.domain.Cliente;
import com.natanjesus.cursomc.domain.enumeration.TipoCliente;
import com.natanjesus.cursomc.dto.ClienteNewDTO;
import com.natanjesus.cursomc.repository.ClienteRepository;
import com.natanjesus.cursomc.resources.exception.FieldMessage;
import com.natanjesus.cursomc.services.validation.utils.validationBR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements  ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDTO.getTipo() == null) {
            list.add(new FieldMessage("tipo", "Tipo não pode ser nulo"));
        } else {
            if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
                    && !validationBR.isValidCPF(clienteNewDTO.getDocumento())) {
                list.add(new FieldMessage("documento", "CPF é inválido"));
            }
            if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOJURIDICA.getCod())
                    && !validationBR.isValidCNPJ(clienteNewDTO.getDocumento())) {
                list.add(new FieldMessage("documento", "CNPJ é inválido"));
            }
        }

        if(this.clienteRepository.existsByEmail(clienteNewDTO.getEmail())){
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
