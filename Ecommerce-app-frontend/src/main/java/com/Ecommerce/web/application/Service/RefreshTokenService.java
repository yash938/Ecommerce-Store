package com.Ecommerce.web.application.Service;


import com.Ecommerce.web.application.Dto.RefreshTokenDto;
import com.Ecommerce.web.application.Dto.UserDto;

public interface RefreshTokenService {

     public RefreshTokenDto createRefreshToken(String username);

     public RefreshTokenDto findByToken(String token);

     public RefreshTokenDto verifyToken(RefreshTokenDto refreshTokenDto);

     UserDto getUser(RefreshTokenDto tokenDto);

}
