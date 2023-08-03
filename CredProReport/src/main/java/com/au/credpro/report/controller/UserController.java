package com.au.credpro.report.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credpro/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/adduser")
    public ResponseEntity<User> addUsers(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
    @PostMapping("/assign-queries//{userId}")
    public ResponseEntity<String> assignQueriesToUser(
  //          @PathVariable Long adminId,
            @PathVariable Long userId,
            @RequestBody List<Long> queryIds) {
    	System.out.println("hello-------------");
        userService.assignQueriesToUser( userId, queryIds);
    	
        return ResponseEntity.ok("Queries assigned to the user successfully.");
    }
    
    
    
    @GetMapping("/{userAuId}/queries")
    public ResponseEntity<List<QueryList>> getQueriesByUserId(@PathVariable Long userAuId) {
    	 try {
    	        User user = userService.getUserByUserAuId(userAuId);
    	        Long userId = user.getUserId();
    	        List<QueryList> queries = userService.getQueriesByUserId(userId);
    	        return ResponseEntity.ok(queries);
    	    } catch (EntityNotFoundException e) {
    	        return ResponseEntity.notFound().build();
    	    } catch (Exception e) {
    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	    }
    	}
}
    
    
    

	

