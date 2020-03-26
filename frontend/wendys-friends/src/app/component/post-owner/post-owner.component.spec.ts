import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostOwnerComponent } from './post-owner.component';

describe('PostOwnerComponent', () => {
  let component: PostOwnerComponent;
  let fixture: ComponentFixture<PostOwnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostOwnerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
