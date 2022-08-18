package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.domain.UserDTO;
import com.board.service.BoardService;
import com.board.service.UserService;
import com.board.util.UiUtils;

@Controller// 해당 클래스가 사용자의 요청과 응답을 처리하는 , 즉 UI를 담당하는 컨트롤러 클래스임을 의미.
public class BoardController extends UiUtils {

   @Autowired
   private BoardService boardService;
   
   @Autowired
   private UserService userService;

   @GetMapping(value = "/board/write") // 그냥 내가 정하는 요청 url 이렇게 요청하면 return 경로로 갈거에요.
   public String openBoardWrite(@RequestParam(value="idx",required=false)Long idx,Model model, HttpSession session) 
   {    // 새로운 게시글을 등록하는 경우에는 게시글 번호(idx) 가 필요하지 않기 때문에 required 속성을 false 
      if(idx==null) {
         // addAttribute BoardDTO 객체를 "board" 라는 이름으로 뷰(화면으로) 전달
         // 게시글 번호(idx)가 전송되지 않은 경우에는 비어있는 객체를 전달하고, 게시글번호가 
         // 전송된 경우에는 getBoardDetail 메서드의 실행결과 ,즉 게시글 정보를 포함하고 있는 객체를 전달
         // 만약 getBoardDetail 메서드의 실행결과가 null 이면, 게시글 리스트 페이지로 리다이렉트 합니다.
         String myID = (String)session.getAttribute("id");
         UserDTO user = userService.getUserDetail(myID);
         //세션에서 가져온 아이디를 기준으로 dto 정보를 getdetail 을해서 다 가져온다
               
         BoardDTO dao1 = new BoardDTO();// dao1 이라는 새로운 인스턴스를 생성
         dao1.setWriter(user.getNickname());//그 BoardDTO 에 닮겨있는 writer 에 dao1을 통해 writer를 지정
         System.out.println("제발 되었으면"+dao1.getWriter());
         model.addAttribute("board",dao1); // "board" 라는 key 에 dao1의 value를 입력

         
      } else { 
         BoardDTO board=boardService.getBoardDetail(idx);
         if(board==null) {
            return "redirect:/board/list";
         }
         model.addAttribute("board", board);//addAttribute 화면으로 데이터를 전달하는메소드 
         System.out.println("board.getNoticeYn() : " + board.getNoticeYn());
      }
      return "board/write";
   }  
   
   @PostMapping(value = "/board/register")
   public String registerBoard(HttpSession session, final BoardDTO params,Model model) {
      System.out.println("boardcontroller 입성");
      try {
         System.out.println("여기까지는 갈꺼야 ");
         System.out.println(params.getWriter());
         System.out.println(params.getIdx());
         System.out.println(params.getContent());
         
         boolean isRegistered = boardService.registerBoard(params);
         
         
         
         System.out.println("isregistered true"+isRegistered);
         if (isRegistered == false) {
            System.out.println("isregistered false"+isRegistered);
            return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list", Method.GET, null, model);
         }
         
         
         
      } catch (DataAccessException e) {
         
         return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

      } catch (Exception e) {
         return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
      }

      return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list", Method.GET, null, model);
   }
   
   // value 값으로 호출하면 openboardlist 함수가 실행

   
   @GetMapping(value="/board/list")
   public String openBoardList(@RequestParam String type,@ModelAttribute("boardCriteria") BoardDTO params,Model model) {
      System.out.println("params.getTitle:"+params.getTitle());
      List<BoardDTO> boardList=boardService.getBoardList(params);
      model.addAttribute("boardlist", boardList);
      return "board/list";
   }
   
   
   @GetMapping(value = "/board/view")
   public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
      if (idx == null) {
         // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
         return "redirect:/board/list";
      }

      BoardDTO board = boardService.getBoardDetail(idx);
      if (board == null || "Y".equals(board.getDeleteYn())) {
         // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
         return "redirect:/board/list";
      }
      System.out.println("board.idx:"+board.getIdx());
      model.addAttribute("board", board);

      return "board/view";
   }
   
   @PostMapping(value = "/board/delete")
   public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx, Model model) {
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

      return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list", Method.GET, null, model);
   }
}




