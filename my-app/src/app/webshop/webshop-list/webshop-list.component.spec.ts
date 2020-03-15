import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WebshopListComponent } from './webshop-list.component';

describe('WebshopListComponent', () => {
  let component: WebshopListComponent;
  let fixture: ComponentFixture<WebshopListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WebshopListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WebshopListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
