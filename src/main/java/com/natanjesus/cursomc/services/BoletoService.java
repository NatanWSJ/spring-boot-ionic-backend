package com.natanjesus.cursomc.services;

import com.natanjesus.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagTo, Date instantePedido) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instantePedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagTo.setDataVencimento(calendar.getTime());
    }

}
