package com.baizhi;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.util.SizeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestAdminDAO {

    @Resource
    private AdminDAO adminDAO;
    @Resource
    private BannerDAO bannerDAO;

    @Resource
    private AlbumDAO albumDAO;
    @Resource
    private ArticleDAO articleDAO;

    @Resource
    private UserDAO userDAO;
    @Test
    public void test1(){
        Admin admin = adminDAO.selectByAdminnameAndPassword(new Admin("","admin", "admin"));
        System.out.println(admin);
    }
    @Test
    public void test2(){
        List<Banner> banners = bannerDAO.selectAll(1, 1);
        Integer integer = bannerDAO.selectCount();
        System.out.println(integer);
        for (Banner banner : banners) {
            System.out.println(banner);
        }

    }
    @Test
    public void test3(){
        Banner banner = new Banner();
        banner.setId("2842a3b7-b526-4f80-ba87-dc5474443d74");
        banner.setName("xxx11");
        banner.setDescription("秀2");
        banner.setImg_path("1.jpg");
        banner.setStatus("冻结");
        banner.setUp_date(new Date());
        bannerDAO.deleteById("2842a3b7-b526-4f80-ba87-dc5474443d74");
    }
    @Test
    public void test4(){
//        List<Album> albums = albumDAO.selectAll(1, 2);
//        for (Album album : albums) {
//            System.out.println(album);
//        }
        Album a= new Album();
        a.setId("adae199e-76e9-478a-8677-1341637aca20");
        a.setAuthor("马给1");
        a.setContent("xox1ix");
        a.setCounts(2);
        a.setCover("1.jpg");
        a.setCrea_date(new Date());
        a.setScore(1.0);
        a.setTitle("无误");
        a.setBroadcast("1111");
        albumDAO.deleteById("adae199e-76e9-478a-8677-1341637aca20");
    }

    @Test
    public void method2() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\1.mp3");
        String size = SizeUtil.getSize(file);
        System.out.println(size);
        String duration = SizeUtil.getDuration(file);
        System.out.println(duration);
    }

    @Test
    public void test5(){
        Article article = new Article();
        article.setAuthor("单色1");
        article.setContent("秀12");
        article.setCrea_date(new Date());
        article.setId("eb9fd0ab14e849bdb24d4d4009b32f1d");
        article.setGuru_id("11");
        article.setTitle("ds");
        articleDAO.deleteById("eb9fd0ab14e849bdb24d4d4009b32f1d");
    }
    @Test
    public void test6(){
        User user = new User();
        user.setId("f51bd04972ad4808b93597f196062858");
        user.setName("a12ei");
        user.setAvatar("123");
        user.setCrea_date(new Date());
        user.setCity("123");
        user.setGuru_id("1");
        user.setLaw_name("123");
        user.setPhone("1231");
        user.setSex("nam23");
        user.setSign("1231");
        user.setStatus("f23g");
        userDAO.deleteById("f51bd04972ad4808b93597f196062858");

    }
}
