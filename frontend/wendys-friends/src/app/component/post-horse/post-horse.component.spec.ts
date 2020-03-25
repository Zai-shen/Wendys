import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostHorseComponent } from './post-horse.component';

describe('PostHorseComponent', () => {
  let component: PostHorseComponent;
  let fixture: ComponentFixture<PostHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
