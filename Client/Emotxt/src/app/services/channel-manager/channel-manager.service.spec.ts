import { TestBed } from '@angular/core/testing';

import { ChannelManagerService } from './channel-manager.service';

describe('ChannelManagerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChannelManagerService = TestBed.get(ChannelManagerService);
    expect(service).toBeTruthy();
  });
});
