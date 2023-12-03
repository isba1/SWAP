package com.SwapToSustain.Server.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserNotification {
  private UUID id;
  private boolean isAccepted;
  private String userName;
  private UUID userID;
  private String postName;
}
