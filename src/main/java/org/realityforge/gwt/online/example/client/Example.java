package org.realityforge.gwt.online.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import javax.annotation.Nonnull;
import org.realityforge.gwt.online.client.OnlineIndicator;
import org.realityforge.gwt.online.client.event.OffLineEvent;
import org.realityforge.gwt.online.client.event.OnLineEvent;

public final class Example
  implements EntryPoint
{
  public void onModuleLoad()
  {
    final VerticalPanel panel = new VerticalPanel();
    final OnlineIndicator indicator = OnlineIndicator.getOnlineIndicatorIfSupported();
    if ( null == indicator )
    {
      Window.alert( "OnlineIndicator is not available!" );
    }
    else
    {
      final VerticalPanel textPanel = new VerticalPanel();

      indicator.addOnLineHandler( new OnLineEvent.Handler()
      {
        @Override
        public void onOnLineEvent( @Nonnull final OnLineEvent event )
        {
          appendText( textPanel, "Online!", "green" );
        }
      } );
      indicator.addOffLineHandler( new OffLineEvent.Handler()
      {
        @Override
        public void onOffLineEvent( @Nonnull final OffLineEvent event )
        {
          appendText( textPanel, "Offline!", "red" );
        }
      } );

      if ( indicator.isOnLine() )
      {
        appendText( textPanel, "Initial status: Online", "green" );
      }
      else
      {
        appendText( textPanel, "Initial status: Offline", "red" );
      }

      panel.add( textPanel );
      RootPanel.get().add( panel );
    }
  }

  private void appendText( final VerticalPanel panel, final String eventName, final String color )
  {
    panel.add( new InlineHTML( "<span style=\"color:" + color + "\">" + eventName + "</span><br />" ) );
  }
}
