import { TestBed, inject } from '@angular/core/testing';

import {JWT_OPTIONS, JwtHelperService} from '@auth0/angular-jwt';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthService, JwtHelperService, JWT_OPTIONS]
    });
  });

  it('should be created', inject([AuthService], (service: AuthService) => {
    expect(service).toBeTruthy();
  }));
});
