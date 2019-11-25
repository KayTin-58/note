package com.example.tutorial;


import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author zb 2019/07/27 13:38
 */
@Slf4j
public class MainTest {
    public static void main(String[] args) {
        AddressBookProtos.AddressBook addressBook =
                        AddressBookProtos.AddressBook.newBuilder().setField(null, "dasas").build();

        byte[] bytes = addressBook.toByteArray();

        try {
            AddressBookProtos.AddressBook address1 = AddressBookProtos.AddressBook.parseFrom(bytes);
            log.info(address1.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
