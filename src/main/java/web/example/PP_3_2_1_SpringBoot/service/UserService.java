package web.example.PP_3_2_1_SpringBoot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.example.PP_3_2_1_SpringBoot.dao.UserDao;
import web.example.PP_3_2_1_SpringBoot.model.User;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@Service
public class UserService {

    private final UserDao userDao;
    private final SecureRandom secureRandom;
    private final KeyGenerator keyGenerator;
    private final SecretKey secretKey;
    private Cipher cipher;

    {
        try {
            secureRandom = new SecureRandom();
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, secureRandom);
            secretKey = keyGenerator.generateKey();
            cipher = Cipher.getInstance("AES");
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Autowired
    UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers(){
        return userDao.getUsers();
    }

    public User showUser(Long id){
        return decodePassword(userDao.showUser(id));
    }
    public void addUser(User user) {
        userDao.addUser(encodePassword(user));
    }

    public void updateUser(User user) {
        userDao.updateUser(encodePassword(user));
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    private User encodePassword(User user){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(user.getPassword().getBytes(StandardCharsets.UTF_8));
            user.setPassword(Base64.getEncoder().encodeToString(cipherText));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    private User decodePassword(User user){
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(user.getPassword()));
            user.setPassword(new String(cipherText));
        } catch(InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

}