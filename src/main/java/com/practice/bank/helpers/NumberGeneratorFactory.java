package com.practice.bank.helpers;

import com.practice.bank.repository.AccountRepository;
import com.practice.bank.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class NumberGeneratorFactory {
    private final Map<Class<?>, Supplier<NumberGenerator>> generatorMap;
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public NumberGeneratorFactory(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        generatorMap = new HashMap<>();
        registerGenerators();
    }

    private void registerGenerators() {
        generatorMap.put(CardNumberGenerator.class, () -> new CardNumberGenerator(cardRepository));
        generatorMap.put(CVVGenerator.class, () -> new CVVGenerator(cardRepository));
        generatorMap.put(AccountNumberGenerator.class, () -> new AccountNumberGenerator(accountRepository));
    }

    public <T extends NumberGenerator> NumberGenerator getGenerator(Class<T> dependencyClass){
        Supplier<NumberGenerator> generatorSupplier = generatorMap.get(dependencyClass);
        if (generatorSupplier == null){
            throw new IllegalArgumentException("Unsupported dependency class: " + dependencyClass);
        }
        return generatorSupplier.get();
    }
}
