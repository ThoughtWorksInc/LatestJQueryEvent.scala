package com.thoughtworks.binding
import com.thoughtworks.binding.Binding.{ChangedEvent, ChangedListener}
import org.scalajs.jquery._

import scala.scalajs.js

/**
  * @author 杨博 (Yang Bo)
  */
class LatestJQueryEvent(eventTarget: JQuery, eventType: js.Any) extends Binding[Option[JQueryEventObject]] {
  private var cache: Option[JQueryEventObject] = None
  private val publisher = new SafeBuffer[ChangedListener[Option[JQueryEventObject]]]

  protected final def removeChangedListener(listener: ChangedListener[Option[JQueryEventObject]]): Unit = {
    publisher -= listener
    if (publisher.isEmpty) {
      eventTarget.off(eventType, eventListener)
    }
  }

  private val eventListener: js.Function1[JQueryEventObject, js.Any] = { upstreamEvent: JQueryEventObject =>
    cache = Some(upstreamEvent)
    val changedEvent = new ChangedEvent(this, cache)
    for (listener <- publisher) {
      listener.changed(changedEvent)
    }
  }

  protected final def addChangedListener(listener: ChangedListener[Option[JQueryEventObject]]): Unit = {
    if (publisher.isEmpty) {
      eventTarget.on(eventType, eventListener)
    }
    publisher += listener
  }

  protected final def value: Option[JQueryEventObject] = cache
}
