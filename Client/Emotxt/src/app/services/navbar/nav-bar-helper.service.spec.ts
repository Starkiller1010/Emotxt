import { TestBed } from '@angular/core/testing';

import { NavBarHelperService } from './nav-bar-helper.service';

describe('NavBarHelperService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NavBarHelperService = TestBed.get(NavBarHelperService);
    expect(service).toBeTruthy();
  });
});
