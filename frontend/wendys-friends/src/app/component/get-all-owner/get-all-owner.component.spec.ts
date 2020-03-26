import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllOwnerComponent } from './get-all-owner.component';

describe('GetAllOwnerComponent', () => {
  let component: GetAllOwnerComponent;
  let fixture: ComponentFixture<GetAllOwnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllOwnerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
