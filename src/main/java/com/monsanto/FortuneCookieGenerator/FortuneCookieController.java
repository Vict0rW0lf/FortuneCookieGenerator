package com.monsanto.FortuneCookieGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FortuneCookieController {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private FortuneCookieBuilder fortuneCookieBuilder;
    
    private String lastQuote;

    @GetMapping(path = "/generateFortuneCookie", produces = {"application/json"})
    public @ResponseBody FortuneCookie generateFortuneCookie(@RequestParam("client") String client,
                          @RequestParam("company") String company) {
        String quote = quoteRepository.getRandomQuote();
                
        // prevents fortune cookie from having the same value twice in a row
        while (quote.equals(lastQuote)) {
        	quote = quoteRepository.getRandomQuote();
        }
        
        lastQuote = quote;
        
        FortuneCookie fortuneCookie = fortuneCookieBuilder.withClient(client).
                withCompany(company).
                withQuote(quote).
                build();
                
        return fortuneCookie;
    }

}
