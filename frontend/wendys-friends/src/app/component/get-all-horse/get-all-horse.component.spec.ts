import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllHorseComponent } from './get-all-horse.component';

describe('GetAllHorseComponent', () => {
  let component: GetAllHorseComponent;
  let fixture: ComponentFixture<GetAllHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
