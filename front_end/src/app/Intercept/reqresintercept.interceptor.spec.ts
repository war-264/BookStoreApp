import { TestBed } from '@angular/core/testing';

import { ReqresinterceptInterceptor } from './reqresintercept.interceptor';

describe('ReqresinterceptInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ReqresinterceptInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ReqresinterceptInterceptor = TestBed.inject(ReqresinterceptInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
