package ssafy.musicD.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import ssafy.musicD.dto.User;
import ssafy.musicD.repository.FriendRepolmpl;
import ssafy.musicD.service.FriendServicelmpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class FriendController {
    @Autowired
    private FriendServicelmpl friendService;

    @ApiOperation(value = "친구 검색", response = String.class)
    @GetMapping("/friend/{keyword}")
    public List<User> searchFriends(@PathVariable String keyword) {
        List<User> searchFriendList = friendService.searchFriends(keyword);
        return searchFriendList;
    }

//    @ApiOperation(value = "친구 아닌 유저 검색", response = String.class)
//    @GetMapping("/nonfriend/{keyword}")
//    public List<User> searchNonFriends(@PathVariable String keyword) {
//        List<User> searchNonFriendList = friendService.searchNonFriend(keyword);
//        return searchNonFriendList;
//    }
//
//    @ApiOperation(value = "친구 목록 조회", response = String.class)
//    @GetMapping("/friend/")
//    public List<User> friendList() {
//        List<User> friendList = friendService.friendList();
//        return friendList;
//    }
//
//    @ApiOperation(value = "친구 요청 보내기", response = String.class)
//    @PostMapping("/friend/request/")
//    public ResponseEntity<Map<String,Object>> requestFriend(HttpServletRequest req, HttpServletResponse res) {
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//
//        try {
//
//            status = HttpStatus.OK;
//            resultMap.put("message", "친구신청 성공하였습니다.");
//
//        } catch (RuntimeException | SQLException e) {
//            status = HttpStatus.BAD_REQUEST;
//            resultMap.put("status", status.value());
//            resultMap.put("message", e.getMessage());
//        }
//
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//    @ApiOperation(value = "친구 요청 수락", response = String.class)
//    @PostMapping("/friend/accept/")
//    public ResponseEntity<Map<String,Object>> acceptFriend(HttpServletRequest req,HttpServletResponse res){
//        Map<String,Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try{
//            status = HttpStatus.OK;
//            resultMap.put("message", "친구 요청에 수락하셨습니다.");
//        } catch (RuntimeException | SQLException e) {
//            status = HttpStatus.BAD_REQUEST;
//            resultMap.put("status", status.value());
//            resultMap.put("message", e.getMessage());
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
//
//    @ApiOperation(value = "나에게 친구요청한 유저 목록 조회", response = String.class)
//    @GetMapping("/friend/request/list/")
//    public List<User> waitFriendList() {
//        List<User> waitFriendList = friendService.waitFriendList();
//        return waitFriendList;
//    }
//
//    @ApiOperation(value="친구 삭제", response=String.class)
//    @DeleteMapping("/friend/{userId}")
//    public ResponseEntity<Map<String, Object>> deleteFriend(@PathVariable String userId) {
//        Map<String, Object> resultMap = null;
//        HttpStatus status = null;
//        try {
//            friendService.deleteFriend(userId);
//            status = HttpStatus.OK;
//            resultMap.put("message", "친구 삭제하셨습니다.");
//        } catch (RuntimeException | SQLException e) {
//            status = HttpStatus.BAD_REQUEST;
//            resultMap.put("status", status.value());
//            resultMap.put("message", e.getMessage());
//        }
//
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }

}
