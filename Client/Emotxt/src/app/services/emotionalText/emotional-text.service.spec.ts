import { TestBed } from '@angular/core/testing';

import { EmotionalTextService } from './emotional-text.service';

describe('EmotionalTextService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmotionalTextService = TestBed.get(EmotionalTextService);
    expect(service).toBeTruthy();
  });
});
