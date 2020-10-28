package com.tts.techtalenttwitter.controller;

import java.util.List;

import javax.validation.Valid;
import com.tts.techtalenttwitter.model.Tweet;
import com.tts.techtalenttwitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalenttwitter.service.TweetService;
import com.tts.techtalenttwitter.service.UserService;


@Controller
public class TweetController {
    
    @Autowired
    private UserService userService;

    @Autowired 
    private TweetService tweetService;

    @GetMapping(value = {"/tweet", "/"})
    public String getFeed(Model model){
        List<Tweet> tweets = tweetService.findAll();
        model.addAttribute("tweetList", tweets);
        return "feed";
    }

    @PostMapping(value = "/tweets")
    public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model){
        User user = userService.getloggedInUser();
        if (!bindingResult.hasErrors()){
            tweet.setUser(user);
            tweetService.save(tweet);
            model.addAttribute("successMessage", "Tweet successfully created!");
            model.addAttribute("tweet", new Tweet());
        }
        return "newTweet";
    }
}