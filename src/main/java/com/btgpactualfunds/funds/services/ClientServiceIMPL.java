package com.btgpactualfunds.funds.services;


import com.btgpactualfunds.funds.entities.Client;
import com.btgpactualfunds.funds.repository.ClientRepositoryIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceIMPL {

    @Autowired
    private ClientRepositoryIMPL clientRepositoryIMPL;


}
