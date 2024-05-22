package com.example.colorfinder.controller;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.ProductDTO;
import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.service.CartService;
import com.example.colorfinder.service.ProductService;
import com.example.colorfinder.service.UserService;
import com.samskivert.mustache.MustacheException;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;



    //메인페이지
    @RequestMapping("/colorfinder")
    public String colorfinderMain(
            @RequestParam(name = "cate", defaultValue = "ALL") String category,
            @RequestParam(name = "personalcolor", defaultValue = "ALL") String personalcolor,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "search", defaultValue = "*") String searchWord,
            Model model)
    throws  ClassNotFoundException, SQLException{

        //로그인 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());

        USERS user = userService.getUserById(userId);

        //로그인한 회원 퍼스널컬러 추출
        String userPersonalcolor = "퍼스널컬러를 진단하여 알아보세요!";
        System.out.println(user.getPersonalColor());
        if (user.getPersonalColor().getColorName()!=null){
            userPersonalcolor = user.getPersonalColor().getColorName();
        }
        model.addAttribute("userPersonalColor", userPersonalcolor);

        //상품목록 생성
        List<ProductDTO> productDTOList = new ArrayList<>();

        //category 필터링 하는 부분
        if (category.equals("ALL")) {
            productDTOList = productService.findAll();
        }else {
            productDTOList = productService.findByCateId(category);
        }

        List<ProductDTO> userRecommendList = productDTOList;

        //personalcolor 필터링 하는 부분
        if (!personalcolor.equals("ALL") && !productDTOList.isEmpty()) {
            try {
                productDTOList = productDTOList.stream()
                        .filter(product -> product.getColorId().equals(personalcolor))
                        .collect(Collectors.toList());

            }catch (Exception e){
                System.out.println(e);
                productDTOList.clear();
            }
        }

        //검색
        if (!searchWord.equals("*") && !productDTOList.isEmpty()) {
            final String searchLowerCase = searchWord.toLowerCase();
            System.out.println(searchLowerCase);
            productDTOList = productDTOList.stream()
                    .filter(product -> product.getProductName().toLowerCase().contains(searchLowerCase))
                    .collect(Collectors.toList());
        }

        //정렬하는 부분
        if (sortBy.equals("lowPrice")) {
            productDTOList = productDTOList.stream().sorted(Comparator.comparing(ProductDTO::getProductPrice)).collect(Collectors.toList());
        } else if (sortBy.equals("highPrice")) {
            productDTOList = productDTOList.stream().sorted(Comparator.comparing(ProductDTO::getProductPrice).reversed()).collect(Collectors.toList());
        }else{
            productDTOList = productDTOList.stream().sorted(Comparator.comparing(ProductDTO::getProductId)).collect(Collectors.toList());
        }
        model.addAttribute("productList", productDTOList);


        //기상청 API에서 기온 불러오기
        int temp = 0;
        temp = getTemp();
        model.addAttribute("temp",temp);


        //유저에게 맞는 제품 추천(기온 + 퍼컬)
        if (userId!=null && user.getPersonalColor().getColorName()!=null){
            int finalTemp = temp;
            productDTOList = productDTOList.stream()
                    .filter(product -> product.getColorId().equals(user.getPersonalColor()) && product.getTemp() < finalTemp)
                    .collect(Collectors.toList());
        }
        model.addAttribute("userRecommendList", userRecommendList);

        return "productlist";
    }

    //상품 상세 페이지
    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
    public String productInfo(@PathVariable Long id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("product",productDTO);
        return "product";
    }


    //구매하기를 눌렀을때
    @RequestMapping(value = "/happy", method = RequestMethod.POST)
    public String saveOrder(Long productId, String action, Integer productCnt, String productSize) {
        Long userId = null;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = Long.parseLong(authentication.getName());
        }catch (Exception e){
            return "redirect:/login";
        }
        if (action.equals("cart")){
            ProductDTO product = productService.findById(productId);

            CartDTO cart = new CartDTO();
            cart.setCartCnt(productCnt);
            cart.setProductSize(productSize);
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setProduct(product);
            cart.setProductName(product.getProductName());
            cart.setTotalPrice(product.getProductPrice() * productCnt);

            cartService.save(cart);
        }else{

        }

        return  "redirect:/colorfinder";
    }




    //기상청 API 관련 파트
    //x 68 y 100 대전시 오정동 좌표
    public static int getTemp(){
        String[] v = new String[5];
        String s = get(68, 100, v);
        int temp;
        if (s==null){
            temp = Integer.parseInt(v[3]);
        }else {
            temp = 0;
        }

        return temp;
    }
    public static void printTemp()
    {
        String[] v = new String[5];
        String s = get(68, 100, v);
        if (s == null)
        { // ok!
            System.out.println("날짜 : " + v[0]);
            System.out.println("시간 : " + v[1]);
            System.out.println("날씨 : " + v[2]);
            System.out.println("기온 : " + v[3] + "℃");
            System.out.println("습도 : " + v[4] + "%");
        }
        else
        { // error
            System.out.println("Error : " + s);
        }
    }


    // [in] x, y : 예보지점 X, Y 좌표
    //=> 행정구역별 x,y 값은 참고문서(https://www.data.go.kr/data/15084084/openapi.do) 내려받아 확인
    // [out] v[0]=예보날짜(yyyyMMdd), v[1]=예보시간(HHmm), v[2]=날씨, v[3]=기온(℃), v[4]=습도(%)
    // 반환값 : 에러메시지, null == OK
    public static String get(int x, int y, String[] v)
    {
        HttpURLConnection con = null;
        String s = null; // 에러 메시지

        try
        {
            LocalDateTime t = LocalDateTime.now().minusMinutes(30); // 현재 시각 30분전

            URL url = new URL(
                    "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"
                            + "?ServiceKey=SII1EKvJiGyRCX%2F9h%2B7x5K%2Bx%2FzcEGmeQ7IOutCH4mMgDIUdDWiO730U%2BnamH43z6TB8Ee5m6c8MMHkPgLdDXww%3D%3D" // 서비스키
                            + "&pageNo=1" // 페이지번호 Default: 1
                            + "&numOfRows=60" // 한 페이지 결과 수 (10개 카테고리값 * 6시간)
                            + "&dataType=XML" // 요청자료형식(XML/JSON) Default: XML
                            + "&base_date=" + t.format(DateTimeFormatter.ofPattern("yyyyMMdd"))  // 발표 날짜
                            + "&base_time=" + t.format(DateTimeFormatter.ofPattern("HHmm")) // 발표 시각
                            + "&nx=" + x // 예보지점의 X 좌표값
                            + "&ny=" + y // 예보지점의 Y 좌표값
            );

            con = (HttpURLConnection)url.openConnection();
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(con.getInputStream());

            boolean ok = false; // <resultCode>00</resultCode> 획득 여부

            Element e;
            NodeList ns = doc.getElementsByTagName("header");
            if (ns.getLength() > 0)
            {
                e = (Element)ns.item(0);
                if ("00".equals(e.getElementsByTagName("resultCode").item(0).getTextContent()))
                    ok = true; // 성공 여부
                else // 에러 메시지
                    s = e.getElementsByTagName("resultMsg").item(0).getTextContent();
            }

            if (ok)
            {
                String fd = null, ft = null; // 가장 빠른 예보 시각
                String pty = null; // 강수형태
                String sky = null; // 하늘상태
                String cat; // category
                String val; // fcstValue

                ns = doc.getElementsByTagName("item");
                for (int i = 0; i < ns.getLength(); i++)
                {
                    e = (Element)ns.item(i);

                    if (ft == null)
                    { // 가져올 예보 시간 결정
                        fd = e.getElementsByTagName("fcstDate").item(0).getTextContent(); // 예보 날짜
                        ft = e.getElementsByTagName("fcstTime").item(0).getTextContent(); // 예보 시각
                    }
                    else if (!fd.equals(e.getElementsByTagName("fcstDate").item(0).getTextContent()) ||
                            !ft.equals(e.getElementsByTagName("fcstTime").item(0).getTextContent()))
                        continue; // 결정된 예보 시각과 같은 시각의 것만 취한다.

                    cat = e.getElementsByTagName("category").item(0).getTextContent(); // 자료구분코드
                    val = e.getElementsByTagName("fcstValue").item(0).getTextContent(); // 예보 값

                    if ("PTY".equals(cat)) pty = val; // 강수형태
                    else if ("SKY".equals(cat)) sky = val; // 하늘상태
                    else if ("T1H".equals(cat)) v[3] = val; // 기온
                    else if ("REH".equals(cat)) v[4] = val; // 습도
                }

                v[0] = fd;
                v[1] = ft;

                if ("0".equals(pty))
                { // 강수형태 없으면, 하늘상태로 판단
                    if ("1".equals(sky)) v[2] = "맑음";
                    else if ("3".equals(sky)) v[2] = "구름많음";
                    else if ("4".equals(sky)) v[2] = "흐림";
                }
                else if ("1".equals(pty)) v[2] = "비";
                else if ("2".equals(pty)) v[2] = "비/눈";
                else if ("3".equals(pty)) v[2] = "눈";
                else if ("5".equals(pty)) v[2] = "빗방울";
                else if ("6".equals(pty)) v[2] = "빗방울눈날림";
                else if ("7".equals(pty)) v[2] = "눈날림";
            }
        }
        catch (Exception e)
        {
            s = e.getMessage();
        }

        if (con != null)
            con.disconnect();

        return s;
    }
}
