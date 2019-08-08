package com.example.community.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LetterWithUser {
    private int fromUserId;
    private int toUserId;
    private String username;
    private int type;
}
