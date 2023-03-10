package com.example.koo.domain.user.dto;

import lombok.Getter;
import java.util.ArrayList;

@Getter
public class GetUsersResponse {
    private ArrayList<UserResult> users;

    public GetUsersResponse(ArrayList<UserResult> users) {
        this.users = users;
    }
}
