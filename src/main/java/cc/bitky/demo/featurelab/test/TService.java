package cc.bitky.demo.featurelab.test;

import lombok.extern.slf4j.Slf4j;

import java.security.*;
import org.apache.commons.codec.binary.Base64;
/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@Slf4j
public class TService {

    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(1024);
        KeyPair pair = keygen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        byte[] privBytes = priv.getEncoded();
        byte[] pubBytes = pub.getEncoded();

//        Base64.encodeBase64String();
        byte[] bytesPri= Base64.decodeBase64("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALbgADqwD5s34Rd4fRqSKDwacmG68II6LT1cwSD3IWtykNH5fg6PZwtCjfE9TCEXSxE+9USpeNZsv4SqNrQ/1MhiNqAmbt0mkWi9gzEgORQ15AYnseQCXGZdoqwCx5iUtSndbUd1mXmCkChoazmzMwfJQ+IzG3/DrImFn0zp4q0nAgMBAAECgYBLLd8qQULJLrmsam4TxE4iF0U6hB1ni/8ZIM6MfMwSXfDHQWffGHjmGiA8MlTfrbHAjz9JUNpKXD6HjWobw7n3TGlaedY5m5Z+mlVpHjy9oVlJ3EOSyorjS//+jzhrVZl2sX+UgYVyrmlXAFA5AaTARZLW/NpDU9RLDYDoM3pxAQJBAOnRYByvN8VBtyWZ5vRejLYbfXkP+4FcosEO7xFWFxGS+Keok2tGmwIXc6Ruhd4kc3xu0fouOO28yKps9JzF4GcCQQDIOWZlOecT9wUu5W/qifk3io0Th2grlIIdu8rv+IXE11CE5cUThaBXf8f2cxVhROlz+DIvYAUU6y8ePg3XOdVBAkEAv3cmfxa0UqEnenD2gLFUBGOqcZI2342d8P1rowSeS1PQrmoKndPpZuMRXVrr4CZ1MzYEWBu1nmfy4ivqet5GjQJBAJm2f2bNGhrYtFD5cX8Ep6A9LOtGM/HpFC9RgY3TJJcmmbk9UJwZJSyne9M3LL29EuCmNZjrRVt25nh4OVX3H0ECQD7Oj0CGJOcFIwsJ73h1ZVJl5ZRO8yMh6YHGMMRA28rzCGzguQdpWgqZxzAfP0j9tYiB24PR24OtaK9ziPI+GJk=");
        byte[] bytesPub= Base64.decodeBase64("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCHWay0DVvyNcE7SefYbj2gQ000jh3wVsV9YmGzYXJvuxzmXcOYPhuKG1KCW+8Li031paRRHz8bdFsUM9VAZCPlZhOqR2xhcXv0nfU/nYRxqX9tTc4FtixHQl1mH0464mOTL5iSylpuQperMaVwodmw4ru6aKNb8uo44yXe+QqlQIDAQAB");
        System.out.println();

        String a1=Base64.encodeBase64String(bytesPri);
        String a2=Base64.encodeBase64String(bytesPub);
        String b1=Base64.encodeBase64String(privBytes);
        String b2=Base64.encodeBase64String(pubBytes);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(b1);
        System.out.println(b2);
    }
}
