/*
 * package org.judy.store.controller;
 * 
 * import org.judy.store.domain.Store; import
 * org.judy.store.service.StoreService; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * @RestController
 * 
 * @CrossOrigin("*")
 * 
 * @RequestMapping("/store")
 * 
 * @RequiredArgsConstructor public class StoreRestController {
 * 
 * private final StoreService service;
 * 
 * @GetMapping(value = "/read/{mid}", produces =
 * {MediaType.APPLICATION_JSON_VALUE}) public ResponseEntity<Store>
 * getRead(@PathVariable("mid") String mid){
 * 
 * 
 * return new ResponseEntity<Store>(service.getStore(mid) , HttpStatus.OK); }
 * 
 * }
 */