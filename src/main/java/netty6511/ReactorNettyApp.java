package netty6511;

import io.netty.channel.epoll.EpollEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.ipc.netty.http.server.HttpServer;

public class ReactorNettyApp {

	private static Logger logger = LoggerFactory.getLogger(ReactorNettyApp.class);

	public static void main(String[] args) {

		HttpServer
				.create(options -> {

					options.listen(8080);
					options.eventLoopGroup(new EpollEventLoopGroup());

					options.afterChannelInit(channel -> {
						logger.debug("\n\nNew connection: " + channel.pipeline());
					});

				})
				.newHandler((request, response) -> response.neverComplete())
				.block();

	}

}
