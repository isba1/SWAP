package com.SwapToSustain.Server.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserNotification {
  private UUID id;
  private String status;
  private boolean accepted;
  private String userName;
  private String postName;

}
