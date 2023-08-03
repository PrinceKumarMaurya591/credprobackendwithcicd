package com.au.credpro.report.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.credpro.report.entity.User;
import com.au.credpro.report.repository.AdminRepository;
import com.au.credpro.report.service.AdminService;
import com.au.credpro.report.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credpro/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
    private UserService userService;
	
	@Autowired 
	private AdminRepository adminRepository;
	
	
	
//	 @PostMapping("/{adminId}/add-user")
//	 public ResponseEntity<User> addUserByAdmin(@PathVariable Long adminId,@RequestBody User user) {
//	      
//	       User newUser= adminService.addUserByAdmin(adminId, user);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//	    }
	 
	 
	 @PostMapping("/{adminAuId}/add-user")
	    public ResponseEntity<User> addUserByAdminAuId(@PathVariable Long adminAuId, @RequestBody User user) {
	        User savedUser = adminService.addUserByAdminAuId(adminAuId, user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }
	
	
//	 @PostMapping("/{adminId}/add-user")
//	    public ResponseEntity<User> addUserByAdmin(@PathVariable Long adminId, @RequestBody User user) {
//	        User newUser = adminService.addUserByAdmin(adminId, user);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//	    }
//	
	 
	 
	 
	 
//	 @PostMapping("/{userId}/assign-queries")
//	    public ResponseEntity<User> assignQueriesToUser(
//	            @PathVariable Long userId,
//	            @RequestBody QueryIdsWrapper queryIdsWrapper) {
//	        Set<Long> queryIds = queryIdsWrapper.getQueryIds();
//	        User user = adminService.assignQueriesToUser(userId, queryIds);
//	        return ResponseEntity.ok(user);
//	    }
//	 
	 
	 
	 
//	 @PostMapping("/{userAuId}/assign-queries")
//	 public ResponseEntity<User> assignQueriesToUserByAuId(
//	         @PathVariable Long userAuId,
//	         @RequestBody QueryIdsWrapper queryIdsWrapper) {
//	     Set<Long> queryIds = queryIdsWrapper.getQueryIds();
//	     User user = adminService.assignQueriesToUserByAuId(userAuId, queryIds);
//	     return ResponseEntity.ok(user);
//	 }
		 
	 @PostMapping("/{adminAuId}/assign-queries")
	 public ResponseEntity<User> assignQueriesToUserByAuId(
	       
	         @RequestBody QueryIdsWrapper queryIdsWrapper) {
		 Long userAuId = queryIdsWrapper.getUserAuId();
	     Set<Long> queryIds = queryIdsWrapper.getQueryIds();
	     User user = adminService.assignQueriesToUserByAuId(userAuId, queryIds);
	     return ResponseEntity.ok(user);
	 }
	 
	 
	 @DeleteMapping("/{userId}")
	    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
	        try {
	            adminService.deleteUserById(userId);
	            return ResponseEntity.ok("User deleted successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
	        }
	    }
	 

     }
	        
	    
	 
	 

