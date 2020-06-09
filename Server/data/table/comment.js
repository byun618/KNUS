module.exports = (sequelize, DataTypes) => {
    return sequelize.define('comment', {

        comment_id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            allowNull: false
        },
        board_id: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        contents: {
            type: DataTypes.STRING(45),
            allowNull: false
        },
        time: {
            type: DataTypes.STRING(45),
            allowNull: false
        },
        user_id: {
            type: DataTypes.STRING(10),
            allowNull: false
        }
    },{
        timestamps: false,
        tableName: 'comment'
    })
}