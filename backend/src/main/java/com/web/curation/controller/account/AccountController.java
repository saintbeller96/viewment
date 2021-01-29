package com.web.curation.controller.account;

import com.web.curation.commons.ErrorResponse;
import com.web.curation.dto.user.UserDto;
import com.web.curation.exceptions.UserDuplicateException;
import com.web.curation.exceptions.UserNotFoundException;
import com.web.curation.service.user.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })*/

@Api(tags = {"1. Account"})
@CrossOrigin(origins = {"*"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    /*private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;*/
    private final AccountService accountService;

    @ApiOperation(value = "회원 가입", response = String.class)
    @ApiResponse(code = 201, message = "created")
    @PostMapping("")
    public ResponseEntity<?> join(@RequestBody UserDto userDto){
        accountService.join(userDto);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }


    @ApiOperation(value = "회원 계정 수정")
    @PatchMapping("")
    public ResponseEntity<?> modify(@RequestBody UserDto userDto){
        String modifyId = accountService.modify(userDto);
        return new ResponseEntity<String>("Modified: " + modifyId, HttpStatus.OK);
    }


    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id){
        String deleteId = accountService.delete(id);
        return new ResponseEntity<String>("Deleted: " + deleteId, HttpStatus.OK);
    }

    @ApiOperation(value = "중복 이메일 확인")
    @GetMapping("/{email}/check")
    public ResponseEntity<?> checkDuplicatedEmail(@PathVariable(value = "email") String email){
        accountService.validateDuplicateUser(email);
        return ResponseEntity.ok().build();
    }
}