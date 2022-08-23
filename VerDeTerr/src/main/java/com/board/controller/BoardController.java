package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.domain.UserDTO;
import com.board.service.BoardService;
import com.board.service.UserService;
import com.board.util.UiUtils;

@Controller // 해당 클래스가 사용자의 요청과 응답을 처리하는 , 즉 UI를 담당하는 컨트롤러 클래스임을 의미.
public class BoardController extends UiUtils {

   @Autowired
   private BoardService boardService;

   @Autowired
   private UserService userService;

   @GetMapping(value = "/board/select")
   public String selectBoardType() {
      return "board/select";
   }

   @GetMapping(value = "/board/write") // 그냥 내가 정하는 요청 url 이렇게 요청하면 return 경로로 갈거에요.
   public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx,
         @RequestParam(required = false) String type, Model model, HttpSession session) { // 새로운 게시글을 등록하는 경우에는 게시글
                                                                        // 번호(idx) 가 필요하지 않기 때문에
                                                                        // required 속성을 false
      String myID = (String) session.getAttribute("id");
      UserDTO user = userService.getUserDetail(myID);
      // 세션에서 가져온 아이디를 기준으로 dto 정보를 getdetail 을해서 다 가져온다

      if (idx == null) {
         // addAttribute BoardDTO 객체를 "board" 라는 이름으로 뷰(화면으로) 전달
         // 게시글 번호(idx)가 전송되지 않은 경우에는 비어있는 객체를 전달하고, 게시글번호가
         // 전송된 경우에는 getBoardDetail 메서드의 실행결과 ,즉 게시글 정보를 포함하고 있는 객체를 전달
         // 만약 getBoardDetail 메서드의 실행결과가 null 이면, 게시글 리스트 페이지로 리다이렉트 합니다.
         BoardDTO dao1 = new BoardDTO();// dao1 이라는 새로운 인스턴스를 생성
         dao1.setWriter(user.getNickname());// 그 BoardDTO 에 닮겨있는 writer 에 dao1을 통해 writer를 지정
         model.addAttribute("board", dao1); // "board" 라는 key 에 dao1의 value를 입력

      } else {
         
         BoardDTO board = boardService.getBoardDetail(idx);
         if (board == null) {
            String url = "redirect:/board/list?type=" + type;
            return url;
         }
         model.addAttribute("board", board); // addAttribute 화면으로 데이터를 전달하는메소드
         System.out.println("board.getNoticeYn() : " + board.getNoticeYn());
      }
      System.out.println("type 잘 찍혀?"+type);
      model.addAttribute("type", type);
      
      return "board/write";
   }

   @PostMapping(value = "/board/register")
   public String registerBoard(RedirectAttributes redirectAttributes, HttpSession session, final BoardDTO params,
         Model model) {
      System.out.println("boardcontroller 입성");
      try {
         System.out.println("여기까지는 갈꺼야 ");
         System.out.println(params.getIdx());
         System.out.println(params.getContent());

         String myID = (String) session.getAttribute("id");
         UserDTO user = userService.getUserDetail(myID);
         String myNickname = user.getNickname();
         params.setWriter(myNickname);
         boolean isRegistered = boardService.registerBoard(params);

         System.out.println("isregistered true" + isRegistered);
         if (isRegistered == false) {
            System.out.println("isregistered false" + isRegistered);
            return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list", Method.GET, null, model);
         }

      } catch (DataAccessException e) {

         return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

      } catch (Exception e) {
         return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
      }
      String url = "/board/list?type=" + params.getPostType();
      System.out.println("url 잘찍히는지 이게 안되서 당연히 그런거다."+url);
      return showMessageWithRedirect("게시글 등록이 완료되었습니다.", url, Method.GET, null, model);
   }

   // value 값으로 호출하면 openboardlist 함수가 실행

   @GetMapping(value = "/board/list")
   public String openBoardList(HttpSession session, @RequestParam(required = false) String type,
      /*ModelAttribute 를 통해서, params 라는 이름으로 list.html 단으로 보낸다. */@ModelAttribute("params") BoardDTO params, Model model) {
      //여기서 PostType에 type를 넣어준다. 
      System.out.println("board/list controller에 들어오고, type은 잘가져오는지"+type);
      params.setPostType(type);
      System.out.println("그렇다면 params에 잘 넣는지 여기가 중요하다"+params);
      List<BoardDTO> boardList = boardService.getBoardList(params);
      model.addAttribute("boardlist", boardList);
      //type 으로 list.html 로 보내야한다. 그러면 거기서 isMytype을 통해서 write를 쓸때 type 을 전달해준다. 
      model.addAttribute("type",type);
      
      String myID = (String) session.getAttribute("id");
      UserDTO user = userService.getUserDetail(myID);
      if(!user.getUserType().equals(type)) {
         model.addAttribute("isMyType", "f");
      }
      
      System.out.println();
      return "board/list";
   }

   @GetMapping(value = "/board/view")
   public String openBoardDetail(HttpSession session, @RequestParam(required=false) String type, @RequestParam(value = "idx", required = false) Long idx, Model model) {
      if (idx == null) {
         // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
         return "redirect:/board/list";
      }

      BoardDTO board = boardService.getBoardDetail(idx);
      if (board == null || board.getDeleteYn()) {
         // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
         return "redirect:/board/list";
      }
      System.out.println("board.idx:" + board.getIdx());
      model.addAttribute("board", board);
      model.addAttribute("type", type);
      
      String myID = (String) session.getAttribute("id");
      UserDTO user = userService.getUserDetail(myID);
      String myNickname = user.getNickname();
      model.addAttribute("myNickname", myNickname);
      return "board/view";
   }

   @PostMapping(value = "/board/delete")
   public String deleteBoard(@RequestParam String type, @RequestParam(value = "idx", required = false) Long idx, Model model) {
      System.out.println("여기로 들어오나요?");
      if (idx == null) {
         return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
      }

      try {
         boolean isDeleted = boardService.deleteBoard(idx);
         if (isDeleted == false) {
            return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list", Method.GET, null, model);
         }
      } catch (DataAccessException e) {
         return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

      } catch (Exception e) {
         return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
      }
      String url = "/board/list?type=" + type;
      return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", url, Method.GET, null, model);
   }
}