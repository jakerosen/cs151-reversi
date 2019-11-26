package controller;

import view.Message;

public interface Valve {
  ValveResponse execute(Message message);
}
