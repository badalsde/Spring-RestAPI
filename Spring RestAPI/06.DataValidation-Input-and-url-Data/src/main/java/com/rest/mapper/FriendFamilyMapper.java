package com.rest.mapper;

import com.rest.dto.FriendFamilyDTO;
import com.rest.entity.FriendFamily;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FriendFamilyMapper {

    public List<FriendFamily> toEntity(List<FriendFamilyDTO> friendFamilyDTOList) {
        List<FriendFamily> friendFamilyList = new ArrayList<>();
        for(FriendFamilyDTO friendFamilyDTO : friendFamilyDTOList){
            FriendFamily friendFamily = new FriendFamily();
            friendFamily.setPhoneNo(friendFamilyDTO.getPhoneNo());
            friendFamily.setFriendAndFamily(friendFamilyDTO.getFriendAndFamily());
            friendFamilyList.add(friendFamily);
        }
        return friendFamilyList;
    }

    public List<FriendFamilyDTO> ToDTOList(List<FriendFamily> friendFamilyList){
        if(friendFamilyList == null){
            return Collections.emptyList();
        }
        return friendFamilyList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FriendFamilyDTO toDTO(FriendFamily friendFamily){
        FriendFamilyDTO friendFamilyDTO = new FriendFamilyDTO();
        friendFamilyDTO.setPhoneNo(friendFamily.getPhoneNo());
        friendFamilyDTO.setFriendAndFamily(friendFamily.getFriendAndFamily());
        return friendFamilyDTO;
    }
}
