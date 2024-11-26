package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import com.company.MyFileDAO;
import com.company.MyFileDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MultipleProcess.do")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 1, // 업로드 파일의 최대 크기 (1MB)
    maxRequestSize = 1024 * 1024 * 10 // 전체 요청 크기 (10MB)
)
public class MultipleProcess extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POST 요청 처리 메소드
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 파일 업로드 경로 설정
            String saveDirectory = getServletContext().getRealPath("/uploads");

            // 여러 파일 업로드 처리
            ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory);
            
            // 업로드된 각 파일에 대해 파일 이름을 변경하고 데이터베이스에 삽입
            for (String oFileName : listFileName) {
                String sFileName = FileUtil.renameFile(saveDirectory, oFileName); // 파일 이름 변경
                insertMyFile(req, oFileName, sFileName); // 데이터베이스에 파일 정보 삽입
            }

            // 업로드 완료 후 파일 목록 페이지로 리다이렉트
            resp.sendRedirect("FileList.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            // 오류 발생 시 에러 메시지 설정 후 업로드 페이지로 포워딩
            req.setAttribute("errorMessage", "파일 업로드 오류");
            req.getRequestDispatcher("FileUploadMain.jsp").forward(req, resp);
        }
    }

    // 파일 정보 데이터베이스에 삽입하는 메소드
    private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
        // 폼에서 제목과 카테고리 가져오기
        String title = req.getParameter("title");
        String[] cateArray = req.getParameterValues("cate");
        
        // 카테고리가 선택되지 않았으면 기본 값 설정
        StringBuffer cateBuf = new StringBuffer();
        if (cateArray == null) {
            cateBuf.append("선택한 항목 없음");
        } else {
            for (String s : cateArray) {
                cateBuf.append(s + ",");
            }
        }

        // MyFileDTO 객체 생성 및 값 설정
        MyFileDTO dto = new MyFileDTO();
        dto.setTitle(title);
        dto.setCate(cateBuf.toString());
        dto.setOfile(oFileName); // 원본 파일 이름
        dto.setSfile(sFileName); // 서버 저장 파일 이름

        // MyFileDAO 객체 생성 후 파일 정보 삽입
        MyFileDAO dao = new MyFileDAO();
        dao.insertFile(dto);
        dao.close(); // DB 자원 반납
    }
}
