USE [TheThreeDB]
GO
/****** Object:  Table [dbo].[Baseroute]    Script Date: 2019/6/4 16:25:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Baseroute](
	[baseNumber] [int] NOT NULL,
	[startAddr] [varchar](100) NOT NULL,
	[destAddr] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Baseroute] PRIMARY KEY CLUSTERED 
(
	[baseNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Car]    Script Date: 2019/6/4 16:25:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Car](
	[carNumber] [varchar](50) NOT NULL,
	[people1Number] [int] NULL,
	[people2Number] [int] NULL,
	[routeNumber] [int] NULL,
 CONSTRAINT [PK_Car] PRIMARY KEY CLUSTERED 
(
	[carNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Driver]    Script Date: 2019/6/4 16:25:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Driver](
	[peopleNumber] [int] NOT NULL,
	[driverNumber] [varchar](50) NOT NULL,
	[peopleName] [varchar](10) NOT NULL,
	[peopleAge] [int] NOT NULL,
	[driverAge] [int] NOT NULL,
	[phoNumber] [varchar](50) NOT NULL,
	[peopleState] [int] NOT NULL,
 CONSTRAINT [PK_Driver] PRIMARY KEY CLUSTERED 
(
	[peopleNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Route]    Script Date: 2019/6/4 16:25:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Route](
	[routeNumber] [int] NOT NULL,
	[startAddr] [varchar](100) NOT NULL,
	[destAddr] [varchar](100) NOT NULL,
	[mAddr] [varchar](200) NULL,
 CONSTRAINT [PK_Route] PRIMARY KEY CLUSTERED 
(
	[routeNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_Driver] FOREIGN KEY([people1Number])
REFERENCES [dbo].[Driver] ([peopleNumber])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_Car_Driver]
GO
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_Driver1] FOREIGN KEY([people2Number])
REFERENCES [dbo].[Driver] ([peopleNumber])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_Car_Driver1]
GO
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_Route] FOREIGN KEY([routeNumber])
REFERENCES [dbo].[Route] ([routeNumber])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_Car_Route]
GO
